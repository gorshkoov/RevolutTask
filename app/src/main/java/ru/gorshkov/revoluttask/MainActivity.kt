package ru.gorshkov.revoluttask

import android.os.Bundle
import ru.gorshkov.revoluttask.base.activity.BaseActivity
import ru.gorshkov.revoluttask.features.currencies.CurrencyFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CurrencyFragment())
            .commit()
    }
}
