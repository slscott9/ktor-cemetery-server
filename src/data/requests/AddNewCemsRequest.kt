package com.cemetery.data.requests

import com.cemetery.data.collections.Cemetery

data class AddNewCemsRequest(
        val newCemList : List<Cemetery>
) {
}