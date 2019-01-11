package com.verse.app.verseapp.koin


import com.verse.app.verseapp.features.sendmoney.presenter.MainPresenter
import org.koin.core.parameter.ParameterDefinition
import org.koin.core.parameter.emptyParameterDefinition
import org.koin.core.scope.Scope
import org.koin.standalone.StandAloneContext


inline fun <reified T : Any> MainPresenter.inject(
    name: String = "",
    scope: Scope? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
) = lazy { StandAloneContext.getKoin().koinContext.get<T>(name, scope, parameters) }

