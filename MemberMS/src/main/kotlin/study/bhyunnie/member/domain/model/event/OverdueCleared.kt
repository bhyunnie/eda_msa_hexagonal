package study.bhyunnie.member.domain.model.event

import study.bhyunnie.member.domain.model.vo.IDName
import java.io.Serializable

class OverdueCleared(
	val idName: IDName,
	val point: Long
):Serializable {
}