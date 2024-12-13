package study.bhyunnie.eda_msa_hexagonal.application.output

import org.springframework.stereotype.Repository
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import java.util.Optional

@Repository
interface RentalCardOutputPort {
	fun loadRentalCard(userId:String):Optional<RentalCard>
	fun save(rentalCard: RentalCard): RentalCard
}