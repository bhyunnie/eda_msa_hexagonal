package study.bhyunnie.book.domain.model.event

import java.io.Serializable

class EventResult(
    val eventType: EventType,
    var success: Boolean,
    val idName: IDName,
    val item:Item,
    val point:Long,
):Serializable {
}