package ru.handh.headshandsdemo.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.handh.headshandsdemo.R
import ru.handh.headshandsdemo.databinding.FragmentAuthBinding
import ru.handh.headshandsdemo.presentation.MainActivity
import ru.handh.headshandsdemo.presentation.utils.InputMask
import ru.handh.headshandsdemo.presentation.utils.extensions.*
import ru.handh.headshandsdemo.presentation.viewmodels.WeatherViewModel


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val weatherViewModel by lazy { (activity as MainActivity).koin.get<WeatherViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)

        setSupportActionBar(binding.tbToolbar, getString(R.string.toolbar_title_auth))

        binding.tilPassword.editText?.onTouchDrawableEnd { editText ->
            editText.showTooltip(context?.getString(R.string.password_tooltip))
        }

        binding.mbLogin.setOnClickListener {
            checkInputs {
                loadWeather()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_auth, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // TODO: Доп логику убрать в Extensions или ViewModel

    private fun checkInputs(successCallback: () -> Unit) {
        if (checkEmail() && checkPassword()) {
            successCallback()
        }
    }

    private fun checkEmail(): Boolean {
        return binding.tilEmail.checkInput(
            InputMask.isEmailValid(binding.tilEmail.editText?.text.toString()),
            "Указан некорректный email"
        )
    }

    private fun checkPassword(): Boolean {
        return binding.tilPassword.checkInput(
            InputMask.isPasswordValid(binding.tilPassword.editText?.text.toString()),
            "Указан некорректный пароль"
        )
    }

    private fun loadWeather() {
        val city = "Moscow"
        weatherViewModel.getWeather(city).observe(viewLifecycleOwner, { weather ->
            binding.root.hideKeyboard()

            if (weather == null) {
                showErrorToast()
            } else {
                binding.root.showSnackbar("${weather.city} ${weather.temp}")
            }
        })
    }

    companion object {

        const val TAG = "AuthFragment"

        fun newInstance(): AuthFragment {
            return AuthFragment()
        }
    }
}