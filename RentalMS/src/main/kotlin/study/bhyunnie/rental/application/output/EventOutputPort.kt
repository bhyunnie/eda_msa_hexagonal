package study.bhyunnie.rental.application.output

import org.springframework.stereotype.Component
import study.bhyunnie.rental.domain.model.event.ItemRented
import study.bhyunnie.rental.domain.model.event.ItemReturned
import study.bhyunnie.rental.domain.model.event.OverdueCleared

@Component
interface EventOutputPort {
	fun occurRentalEvent(itemRented: ItemRented)
	fun occurReturnEvent(itemReturned: ItemReturned)
	fun occurOverdueClearedEvent(overdueCleared: OverdueCleared)
}