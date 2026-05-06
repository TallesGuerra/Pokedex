package com.example.pokedex.data.api

import com.example.pokedex.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitClient: Singleton que gerencia a instância Retrofit
 *
 * Por que singleton?
 * - Uma única instância em toda a app (economiza memória)
 * - Reutiliza conexões HTTP (melhor performance)
 * - Compartilha pool de conexões
 */
object RetrofitClient {

    /**
     * OkHttpClient com timeouts configurados
     * Define quanto tempo aguarda antes de dar timeout
     */
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    /**
     * Retrofit instance (lazy = criado apenas quando primeiro usado)
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.POKEMON_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * PokeApi service (lazy = criado apenas quando primeiro usado)
     * Acesso: RetrofitClient.api
     */
    val api: PokeApi by lazy {
        retrofit.create(PokeApi::class.java)
    }
}