package study.bhyunnie.book.domain.model.event

import java.io.Serializable

open class ItemRented(
	val idName: IDName,
	val item: Item,
	val point: Long
):Serializable {}