package com.project.onepice.weatherapp.domain.commands

/**
 * 作者: 方天文
 * 日期: 2017/6/1:下午11:05
 * 概况:
 */
public interface Command<T> {
    fun execute(): T
}