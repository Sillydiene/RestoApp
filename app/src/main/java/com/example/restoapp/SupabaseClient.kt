
package com.example.restoapp

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.engine.android.Android

object SupabaseClient {

    private const val SUPABASE_URL =
        "https://tfeqcuusglvrkrnqsyrs.supabase.co"
    private const val SUPABASE_KEY = "sb_publishable_lAHLFBXddtjt5hlAAJLd8g_yjDKJ9JW"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Auth)
        install(Postgrest)

        httpEngine = Android.create()
    }
}
