package study.bhyunnie.rental.domain.model.event

import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item
import java.io.Serializable

class EventResult(
    val idName:IDName,
    val item:Item,
    val point: Long,
    val eventType: EventType,
    val success: Boolean
):Serializable {

}