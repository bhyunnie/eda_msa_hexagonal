package study.bhyunnie.rental.domain.model.event

import study.bhyunnie.rental.domain.model.vo.IDName

class PointUseCommand(
    val idName: IDName,
    val point: Long
) {
}