package study.bhyunnie.member.domain.model.event

import study.bhyunnie.member.domain.model.vo.IDName
import java.io.Serializable

open class ItemRented(
	val idName: IDName,
	val item: Item,
	val point: Long
):Serializable {}