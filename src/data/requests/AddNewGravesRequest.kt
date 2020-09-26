package com.cemetery.data.requests

import com.cemetery.data.collections.Grave

data class AddNewGravesRequest(
        val newGraveList : List<Grave>
) {
}