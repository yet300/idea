package com.ideaapp.shared.task.main

import com.arkivanov.decompose.ComponentContext

class DefaultTaskComponent(
    componentContext: ComponentContext,
) : TaskComponent, ComponentContext by componentContext {

}