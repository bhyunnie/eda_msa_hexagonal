package study.bhyunnie.bestbook.domain.event

import study.bhyunnie.bestbook.domain.model.Item
import java.io.Serializable

open class ItemRented(
	val idName: IDName,
	val item: Item,
	val point: Long
):Serializable {}