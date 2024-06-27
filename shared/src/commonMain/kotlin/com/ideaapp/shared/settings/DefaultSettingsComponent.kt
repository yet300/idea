package com.ideaapp.shared.settings

import com.arkivanov.decompose.ComponentContext

class DefaultSettingsComponent(
    componentContext: ComponentContext,
) : SettingsComponent, ComponentContext by componentContext {


}