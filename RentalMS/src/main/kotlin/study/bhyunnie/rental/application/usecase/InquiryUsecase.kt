package study.bhyunnie.rental.application.usecase

import study.bhyunnie.rental.framework.web.dto.RentItemOutputDto
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.ReturnItemOutputDto
import study.bhyunnie.rental.framework.web.dto.UserInputDto
import java.util.Optional

interface InquiryUsecase {
	fun getRentalCard(userInputDto: UserInputDto): Optional<RentalCardOutputDto>
	fun getAllRentItem(userInputDto: UserInputDto): Optional<List<RentItemOutputDto>>
	fun getAllReturnItem(userInputDto: UserInputDto): Optional<List<ReturnItemOutputDto>>
}