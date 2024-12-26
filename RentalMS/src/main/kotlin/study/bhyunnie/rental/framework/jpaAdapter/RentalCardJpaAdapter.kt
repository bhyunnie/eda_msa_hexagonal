package study.bhyunnie.rental.framework.jpaAdapter

import org.springframework.stereotype.Repository
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.domain.model.RentalCard
import java.util.*

@Repository
class RentalCardJpaAdapter(
	private val rentalCardRepository: RentalCardRepository
): RentalCardOutputPort {
	override fun loadRentalCard(userId: String): Optional<RentalCard> {
		return rentalCardRepository.findByMemberId(userId)
	}

	override fun save(rentalCard: RentalCard): RentalCard {
		return rentalCardRepository.save(rentalCard)
	}
}