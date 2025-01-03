package study.bhyunnie.rental.application.usecase

import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item

interface CompensationUsecase {
    fun cancelRentItem(idName:IDName, item:Item):RentalCard
    fun cancelReturnItem(idName: IDName, item:Item, point:Long): RentalCard
    fun cancelMakeAvailableRental(idName:IDName, point:Long): Long
}