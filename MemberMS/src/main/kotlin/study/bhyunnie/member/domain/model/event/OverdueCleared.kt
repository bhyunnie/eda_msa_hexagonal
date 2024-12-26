package study.bhyunnie.member.domain.model.event

import java.io.Serializable

class OverdueCleared(
	val idName: IDName,
	val point: Long
):Serializable {

}