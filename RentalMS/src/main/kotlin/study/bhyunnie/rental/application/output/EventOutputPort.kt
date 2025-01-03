package study.bhyunnie.rental.application.output

import org.springframework.stereotype.Component
import study.bhyunnie.rental.domain.model.event.ItemRented
import study.bhyunnie.rental.domain.model.event.ItemReturned
import study.bhyunnie.rental.domain.model.event.OverdueCleared
import study.bhyunnie.rental.domain.model.event.PointUseCommand
import study.bhyunnie.rental.domain.model.vo.IDName

@Component
interface EventOutputPort {
	fun occurRentalEvent(itemRented: ItemRented)
	fun occurReturnEvent(itemReturned: ItemReturned)
	fun occurOverdueClearedEvent(overdueCleared: OverdueCleared)
	fun occurPointUseCommand(pointUseCommand:PointUseCommand)
}