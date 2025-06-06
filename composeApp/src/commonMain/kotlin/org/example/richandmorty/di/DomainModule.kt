package org.example.richandmorty.di

import androidx.navigation.Navigator
import org.example.richandmorty.domain.GetName
import org.example.richandmorty.domain.NameOperator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    /*factory<NameOperator> { NameOperator() }
    factory<GetName> { GetName(get()) }
    //nueva forma
    factoryOf(::GetName)*/
}