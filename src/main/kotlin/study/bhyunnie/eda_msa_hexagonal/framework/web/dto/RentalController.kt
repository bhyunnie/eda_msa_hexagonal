package study.bhyunnie.eda_msa_hexagonal.framework.web.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.bhyunnie.eda_msa_hexagonal.application.usecase.ClearOverdueItemUsecase
import study.bhyunnie.eda_msa_hexagonal.application.usecase.CreateRentalCardUsecase
import study.bhyunnie.eda_msa_hexagonal.application.usecase.InquiryUsecase
import study.bhyunnie.eda_msa_hexagonal.application.usecase.OverdueItemUsecase
import study.bhyunnie.eda_msa_hexagonal.application.usecase.RentItemUsecase
import study.bhyunnie.eda_msa_hexagonal.application.usecase.ReturnItemUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard

@RestController
class RentalController(
	private val rentItemUsecase: RentItemUsecase,
	private val returnItemUsecase: ReturnItemUsecase,
	private val createRentalCardUsecase: CreateRentalCardUsecase,
	private val overdueItemUsecase: OverdueItemUsecase,
	private val clearOverdueItemUsecase: ClearOverdueItemUsecase,
	private val inquiryUsecase: InquiryUsecase
) {

	@PostMapping("/api/rentalCard")
	fun createRentalCard(
		@RequestBody userInputDto: UserInputDto
	):ResponseEntity<RentalCardOutputDto> {
		val createRentalCard = createRentalCardUsecase.createRentalCard(userInputDto)
		return ResponseEntity.status(HttpStatus.CREATED).body(createRentalCard)
	}

	@GetMapping("/api/rentalCard/{userId}")
	fun getRentalCard(
		@PathVariable userId:String
	): ResponseEntity<RentalCardOutputDto> {
		val rentalCard = inquiryUsecase.getRentalCard(UserInputDto(userId, ""))
		return ResponseEntity.ok(rentalCard.get())
	}

	@GetMapping("/api/rentalCard/{userId}/rentBook")
	fun getAllRentItem(
		@PathVariable userId:String
	):ResponseEntity<List<RentItemOutputDto>> {
		val rentItem = inquiryUsecase.getAllRentItem(UserInputDto(userId,""))
		return ResponseEntity.ok(rentItem.get())
	}

	@GetMapping("/api/rentalCard/{userId}/returnBook")
	fun getAllReturnItem(
		@PathVariable userId:String
	):ResponseEntity<List<ReturnItemOutputDto>> {
		val returnItem = inquiryUsecase.getAllReturnItem(UserInputDto(userId,""))
		return ResponseEntity.ok(returnItem.get())
	}

	@PostMapping("/api/rentalCard/rent")
	fun rentItem(
		@RequestBody userItemInputDto: UserItemInputDto
	): ResponseEntity<RentalCardOutputDto> {
		val rentalCardOutputDto = rentItemUsecase.rentItem(userItemInputDto)
		return ResponseEntity.ok(rentalCardOutputDto)
	}

	@PostMapping("/api/rentalCard/return")
	fun returnItem(
		@RequestBody userItemInputDto: UserItemInputDto
	): ResponseEntity<RentalCardOutputDto> {
		val rentalCardOutputDto = returnItemUsecase.returnItem(userItemInputDto)
		return ResponseEntity.ok(rentalCardOutputDto)
	}

	@PostMapping("/rentalCard/overdue")
	fun overdueItem(
		@RequestBody userItemInputDto: UserItemInputDto
	): ResponseEntity<RentalCardOutputDto> {
		val rentalCardOutputDto = overdueItemUsecase.overdueItem(userItemInputDto)
		return ResponseEntity.ok(rentalCardOutputDto)
	}

}