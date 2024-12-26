package study.bhyunnie.rental.domain.model.event

import study.bhyunnie.rental.domain.model.vo.IDName
import java.io.Serializable

class OverdueCleared(
	val idName: IDName,
	val point: Long
):Serializable {

}