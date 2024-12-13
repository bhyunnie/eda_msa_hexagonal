package study.bhyunnie.rental.framework.jpaAdapter

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import study.bhyunnie.rental.model.RentalCard
import study.bhyunnie.rental.model.vo.RentalCardNo
import java.util.Optional

@Repository
interface RentalCardRepository: JpaRepository<RentalCard, RentalCardNo> {
	@Query("select m from RentalCard m where m.member.id = :id")
	fun findByMemberId(
		@Param("id") member: String
	): Optional<RentalCard>

	@Query("select m from RentalCard m where m.rentalCardNo.no = :id")
	fun findById(
		@Param("id") id: String
	): Optional<RentalCard>
}