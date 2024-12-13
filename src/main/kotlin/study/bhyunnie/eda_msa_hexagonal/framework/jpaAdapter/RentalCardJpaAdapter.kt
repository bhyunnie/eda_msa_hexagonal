package study.bhyunnie.eda_msa_hexagonal.framework.jpaAdapter

import org.springframework.stereotype.Repository
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import java.util.*

@Repository
class RentalCardJpaAdapter(
	private val rentalCardRepository: RentalCardRepository
):RentalCardOutputPort {
	override fun loadRentalCard(userId: String): Optional<RentalCard> {
		return rentalCardRepository.findByMemberId(userId)
	}

	override fun save(rentalCard: RentalCard): RentalCard {
		return rentalCardRepository.save(rentalCard)
	}
}