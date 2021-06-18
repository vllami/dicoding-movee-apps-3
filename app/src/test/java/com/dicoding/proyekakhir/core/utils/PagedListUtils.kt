@file:Suppress("DEPRECATION")

package com.dicoding.proyekakhir.core.utils

import androidx.paging.PagedList
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt

object PagedListUtils {

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> mockingPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        pagedList.apply {
            `when`(this[anyInt()]).then { invocation ->
                invocation.arguments.first().also {
                    list[it as Int]
                }
            }
            `when`(size).thenReturn(list.size)

            return this
        }
    }

}