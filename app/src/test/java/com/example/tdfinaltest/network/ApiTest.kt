package com.example.tdfinaltest.network

import android.text.style.UpdateAppearance
import com.example.tdfinaltest.model.network.ProductsAPI
import com.example.tdfinaltest.model.pojos.DetailsProducts
import com.example.tdfinaltest.model.pojos.Products
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mProductsAPI: ProductsAPI

    //Preparar el test
    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val mRetrofit = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mProductsAPI = mRetrofit.create(ProductsAPI::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllProducts_happy_case() = runBlocking {
        //given
        val mResultList = listOf<Products>(Products(2, "https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-se/img/mob/huawei-nova7-se-mob.png", "Huawei Nova 7 SE 128GB", 347760 ))
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mResultList)))

        //when
        val result = mProductsAPI.getAllProducts()

        //then
        assertThat(result).isNotNull()
        val body = result.body()
        assertThat(body).hasSize(1)
        assertThat(body?.get(0)?.id).isEqualTo(2)

        val request = mMockWebServer.takeRequest()
        assertThat(request.path).isEqualTo("/Himuravidal/FakeAPIdata/products")
    }
}