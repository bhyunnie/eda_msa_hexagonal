package study.bhyunnie.member.domain.model.event

import study.bhyunnie.member.domain.model.vo.IDName
import java.io.Serializable

class PointUseCommand(
    val idName: IDName,
    val point: Long
): Serializable {

}