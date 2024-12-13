package study.bhyunnie.rental.application.output

import org.springframework.stereotype.Repository
import study.bhyunnie.rental.model.RentalCard
import java.util.Optional

@Repository
interface RentalCardOutputPort {
	fun loadRentalCard(userId:String):Optional<RentalCard>
	fun save(rentalCard: RentalCard): RentalCard
}