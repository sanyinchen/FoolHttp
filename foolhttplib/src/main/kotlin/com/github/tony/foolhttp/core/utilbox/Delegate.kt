/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.github.tony.foolhttp.core.utilbox

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by sanyinchen on 16/4/21.
 */

class Delegate<T>(val initializer: () -> T) : ReadWriteProperty<Any?, T> {
    private var value: Any? = null;

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = (initializer()) ?: throw IllegalStateException("initializer is null")
        }

        return value as T;
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value;
    }

}

