package ru.gorshkov.revoluttask.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.gorshkov.revoluttask.R
import ru.gorshkov.revoluttask.pojo.RevolutCurrency
import javax.inject.Inject

class CurrencyUtils @Inject constructor() {
    @DrawableRes
    fun getIcon(currency: RevolutCurrency): Int {
        return when (currency) {
            RevolutCurrency.AUD -> R.drawable.ic_aud
            RevolutCurrency.BGN -> R.drawable.ic_bgn
            RevolutCurrency.BRL -> R.drawable.ic_brl
            RevolutCurrency.CAD -> R.drawable.ic_cad
            RevolutCurrency.CHF -> R.drawable.ic_chf
            RevolutCurrency.CNY -> R.drawable.ic_cny
            RevolutCurrency.CZK -> R.drawable.ic_czk
            RevolutCurrency.DKK -> R.drawable.ic_dkk
            RevolutCurrency.EUR -> R.drawable.ic_eur
            RevolutCurrency.GBP -> R.drawable.ic_gbp
            RevolutCurrency.HKD -> R.drawable.ic_hkd
            RevolutCurrency.HRK -> R.drawable.ic_hrk
            RevolutCurrency.HUF -> R.drawable.ic_huf
            RevolutCurrency.IDR -> R.drawable.ic_idr
            RevolutCurrency.ILS -> R.drawable.ic_ils
            RevolutCurrency.INR -> R.drawable.ic_inr
            RevolutCurrency.ISK -> R.drawable.ic_isk
            RevolutCurrency.JPY -> R.drawable.ic_jpy
            RevolutCurrency.KRW -> R.drawable.ic_krw
            RevolutCurrency.MXN -> R.drawable.ic_mxn
            RevolutCurrency.MYR -> R.drawable.ic_myr
            RevolutCurrency.NOK -> R.drawable.ic_nok
            RevolutCurrency.NZD -> R.drawable.ic_nzd
            RevolutCurrency.PHP -> R.drawable.ic_php
            RevolutCurrency.PLN -> R.drawable.ic_pln
            RevolutCurrency.RON -> R.drawable.ic_ron
            RevolutCurrency.RUB -> R.drawable.ic_rub
            RevolutCurrency.SEK -> R.drawable.ic_sek
            RevolutCurrency.SGD -> R.drawable.ic_sgd
            RevolutCurrency.THB -> R.drawable.ic_thb
            RevolutCurrency.TRY -> R.drawable.ic_try
            RevolutCurrency.USD -> R.drawable.ic_usd
            RevolutCurrency.ZAR -> R.drawable.ic_zar
        }
    }

    @StringRes
    fun getName(currency: RevolutCurrency): Int {
        return when (currency) {
            RevolutCurrency.AUD -> R.string.aud_name
            RevolutCurrency.BGN -> R.string.bgn_name
            RevolutCurrency.BRL -> R.string.brl_name
            RevolutCurrency.CAD -> R.string.cad_name
            RevolutCurrency.CHF -> R.string.chf_name
            RevolutCurrency.CNY -> R.string.cny_name
            RevolutCurrency.CZK -> R.string.czk_name
            RevolutCurrency.DKK -> R.string.dkk_name
            RevolutCurrency.EUR -> R.string.eur_name
            RevolutCurrency.GBP -> R.string.gbp_name
            RevolutCurrency.HKD -> R.string.hkd_name
            RevolutCurrency.HRK -> R.string.hrk_name
            RevolutCurrency.HUF -> R.string.huf_name
            RevolutCurrency.IDR -> R.string.idr_name
            RevolutCurrency.ILS -> R.string.ils_name
            RevolutCurrency.INR -> R.string.inr_name
            RevolutCurrency.ISK -> R.string.isk_name
            RevolutCurrency.JPY -> R.string.jpy_name
            RevolutCurrency.KRW -> R.string.krw_name
            RevolutCurrency.MXN -> R.string.mxn_name
            RevolutCurrency.MYR -> R.string.myr_name
            RevolutCurrency.NOK -> R.string.nok_name
            RevolutCurrency.NZD -> R.string.nzd_name
            RevolutCurrency.PHP -> R.string.php_name
            RevolutCurrency.PLN -> R.string.pln_name
            RevolutCurrency.RON -> R.string.ron_name
            RevolutCurrency.RUB -> R.string.rub_name
            RevolutCurrency.SEK -> R.string.sek_name
            RevolutCurrency.SGD -> R.string.sgd_name
            RevolutCurrency.THB -> R.string.thb_name
            RevolutCurrency.TRY -> R.string.try_name
            RevolutCurrency.USD -> R.string.usd_name
            RevolutCurrency.ZAR -> R.string.zar_name
        }
    }
}