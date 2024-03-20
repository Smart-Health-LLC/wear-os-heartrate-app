package com.progneo.smarthealth.service.sleep

import androidx.wear.protolayout.ActionBuilders
import androidx.wear.protolayout.ModifiersBuilders

internal fun openApplicationClickable() =
    ModifiersBuilders.Clickable.Builder().setOnClick(
        ActionBuilders.LaunchAction.Builder()
            .setAndroidActivity(openMainActivity())
            .build()
    ).build()

internal fun openMainActivity() = ActionBuilders.AndroidActivity.Builder()
    .setMainActivity()
    .build()

internal fun ActionBuilders.AndroidActivity.Builder.setMainActivity():
    ActionBuilders.AndroidActivity.Builder {
    return setPackageName("com.progneo.smarthealth")
        .setClassName("com.progneo.smarthealth.MainActivity")
}
