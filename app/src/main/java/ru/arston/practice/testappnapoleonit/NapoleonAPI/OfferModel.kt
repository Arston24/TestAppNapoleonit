package ru.arston.practice.testappnapoleonit.NapoleonAPI

class OfferModel(
private var id: String,
private var name: String,
private var desc: String,
private var groupName: String,
private var type: String,
private var image: String,
private var price: Float,
private var discount: Float
) {

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDesc(): String {
        return desc
    }

    fun setDesc(desc: String) {
        this.desc = desc
    }

    fun getGroupName(): String {
        return groupName
    }

    fun setgroupName(groupName: String) {
        this.groupName = groupName
    }

    fun getType(): String {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getPrice(): Float {
        return price
    }

    fun setPrice(price: Float) {
        this.price = price
    }

    fun getDiscount(): Float {
        return discount
    }

    fun setDiscount(discount: Float) {
        this.discount = discount
    }

}