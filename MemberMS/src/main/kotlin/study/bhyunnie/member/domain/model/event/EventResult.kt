package study.bhyunnie.member.domain.model.event

import study.bhyunnie.member.domain.model.vo.IDName
import java.io.Serializable

class EventResult(
    val eventType: EventType,
    var success: Boolean,
    var idName: IDName,
    val item:Item,
    var point:Long,
):Serializable {
}