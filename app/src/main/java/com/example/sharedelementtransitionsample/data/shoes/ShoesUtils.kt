package com.example.sharedelementtransitionsample.data.shoes

import androidx.compose.ui.graphics.Color
import com.example.sharedelementtransitionsample.R
import com.example.sharedelementtransitionsample.model.shoes.Brand
import com.example.sharedelementtransitionsample.model.shoes.ShoesDetails
import com.example.sharedelementtransitionsample.model.shoes.Shoe

object ShoesUtils {
    val brandList = listOf(
        Brand(id = 1, name = "Nike", country = "USA"),
        Brand(id = 2, name = "Adidas", country = "Germany"),
        Brand(id = 3, name = "Puma", country = "Germany"),
        Brand(id = 4, name = "Reebok", country = "USA"),
        Brand(id = 5, name = "Under Armour", country = "USA"),
        Brand(id = 6, name = "Asics", country = "Japan")
    )

    val shoeList = listOf(
        Shoe(
            id = 1,
            name = "Air Max 270",
            brand = brandList[0],
            color = Color(0xFF1D3557),
            details = ShoesDetails(
                size = 10.0,
                price = 150.0,
                description = "The Nike Air Max 270 features the first-ever Max Air unit created specifically for Nike Sportswear.",
                availableColors = listOf(Color(0xFFE63946), Color(0xFF457B9D), Color(0xFFA8DADC)),
                ratings = 4.5f
            ),
            image = R.drawable.ic_shoes_1
        ),
        Shoe(
            id = 2,
            name = "UltraBoost 21",
            brand = brandList[1],
            color = Color(0xFFFF9100),
            details = ShoesDetails(
                size = 9.0,
                price = 180.0,
                description = "The Adidas UltraBoost 21 is the most comfortable and responsive running shoe.",
                availableColors = listOf(Color(0xFF1D3557), Color(0xFFF1FAEE), Color(0xFF2A9D8F)),
                ratings = 4.7f
            ),
            image = R.drawable.ic_shoes_2
        ),
        Shoe(
            id = 3,
            name = "RS-X³",
            brand = brandList[2],
            color = Color(0xFF2A9D8F),
            details = ShoesDetails(
                size = 11.0,
                price = 110.0,
                description = "The Puma RS-X³ is a bold, innovative design perfect for street style.",
                availableColors = listOf(Color(0xFF2A9D8F), Color(0xFFE9C46A), Color(0xFF264653)),
                ratings = 4.3f
            ),
            image = R.drawable.ic_shoes_3
        ),
        Shoe(
            id = 4,
            name = "Nano X1",
            brand = brandList[3],
            color = Color(0xFFF4A261),
            details = ShoesDetails(
                size = 8.5,
                price = 130.0,
                description = "The Reebok Nano X1 is a versatile training shoe designed for various workouts.",
                availableColors = listOf(Color(0xFFF4A261), Color(0xFF264653), Color(0xFF2A9D8F)),
                ratings = 4.6f
            ),
            image = R.drawable.ic_shoes_4
        ),
        Shoe(
            id = 5,
            name = "Curry 8",
            brand = brandList[4],
            color = Color(0xFF023047),
            details = ShoesDetails(
                size = 10.5,
                price = 160.0,
                description = "The Under Armour Curry 8 provides exceptional grip and support for basketball players.",
                availableColors = listOf(Color(0xFF023047), Color(0xFF8ECAE6), Color(0xFFFB8500)),
                ratings = 4.8f
            ),
            image = R.drawable.ic_shoes_4
        ),
        Shoe(
            id = 6,
            name = "Gel-Kayano 27",
            brand = brandList[5],
            color = Color(0xFF8D99AE),
            details = ShoesDetails(
                size = 9.5,
                price = 140.0,
                description = "The Asics Gel-Kayano 27 is a top choice for long-distance runners, offering superior comfort and stability.",
                availableColors = listOf(Color(0xFF8D99AE), Color(0xFFEDF2F4), Color(0xFFEF233C)),
                ratings = 4.4f
            ),
            image = R.drawable.ic_shoes_3
        ),
        Shoe(
            id = 7,
            name = "Metcon 6",
            brand = brandList[0],
            color = Color(0xFF006D77),
            details = ShoesDetails(
                size = 11.0,
                price = 130.0,
                description = "The Nike Metcon 6 is ideal for high-intensity training and cross-training workouts.",
                availableColors = listOf(Color(0xFF006D77), Color(0xFF83C5BE), Color(0xFFFFDDD2)),
                ratings = 4.6f
            ),
            image = R.drawable.ic_shoes_2
        ),
        Shoe(
            id = 8,
            name = "NMD_R1",
            brand = brandList[1],
            color = Color(0xFF2B2D42),
            details = ShoesDetails(
                size = 10.0,
                price = 140.0,
                description = "The Adidas NMD_R1 blends modern design with ultimate comfort and durability.",
                availableColors = listOf(Color(0xFF2B2D42), Color(0xFF8D99AE), Color(0xFFEDF2F4)),
                ratings = 4.5f
            ),
            image = R.drawable.ic_shoes_1
        ),
        Shoe(
            id = 9,
            name = "Suede Classic",
            brand = brandList[2],
            color = Color(0xFF264653),
            details = ShoesDetails(
                size = 9.0,
                price = 70.0,
                description = "The Puma Suede Classic offers a timeless style and unmatched comfort for everyday wear.",
                availableColors = listOf(Color(0xFF264653), Color(0xFF2A9D8F), Color(0xFFE76F51)),
                ratings = 4.2f
            ),
            image = R.drawable.ic_shoes_4
        ),
        Shoe(
            id = 10,
            name = "Zig Kinetica",
            brand = brandList[3],
            color = Color(0xFFF77F00),
            details = ShoesDetails(
                size = 8.0,
                price = 120.0,
                description = "The Reebok Zig Kinetica is designed for high energy return and all-day comfort.",
                availableColors = listOf(Color(0xFFF77F00), Color(0xFF023047), Color(0xFF8ECAE6)),
                ratings = 4.3f
            ),
            image = R.drawable.ic_shoes_3
        ),
        Shoe(
            id = 11,
            name = "HOVR Phantom 2",
            brand = brandList[4],
            color = Color(0xFF001219),
            details = ShoesDetails(
                size = 10.0,
                price = 150.0,
                description = "The Under Armour HOVR Phantom 2 offers a perfect blend of cushioning and support for runners.",
                availableColors = listOf(Color(0xFF001219), Color(0xFF005F73), Color(0xFF9B2226)),
                ratings = 4.7f
            ),
            image = R.drawable.ic_shoes_2
        ),
        Shoe(
            id = 12,
            name = "GEL-Nimbus 23",
            brand = brandList[5],
            color = Color(0xFF006D77),
            details = ShoesDetails(
                size = 11.5,
                price = 160.0,
                description = "The Asics GEL-Nimbus 23 delivers plush cushioning and a responsive ride for long-distance running.",
                availableColors = listOf(Color(0xFF006D77), Color(0xFF83C5BE), Color(0xFFFFDDD2)),
                ratings = 4.8f
            ),
            image = R.drawable.ic_shoes_1
        ),
        Shoe(
            id = 13,
            name = "Air Zoom Pegasus 37",
            brand = brandList[0],
            color = Color(0xFF023047),
            details = ShoesDetails(
                size = 9.0,
                price = 120.0,
                description = "The Nike Air Zoom Pegasus 37 provides a lightweight and responsive feel for everyday training.",
                availableColors = listOf(Color(0xFF023047), Color(0xFF8ECAE6), Color(0xFFFB8500)),
                ratings = 4.6f
            ),
            image = R.drawable.ic_shoes_3
        ),
        Shoe(
            id = 14,
            name = "Ozweego",
            brand = brandList[1],
            color = Color(0xFF8ECAE6),
            details = ShoesDetails(
                size = 8.5,
                price = 100.0,
                description = "The Adidas Ozweego offers a modern twist on a retro style, perfect for streetwear enthusiasts.",
                availableColors = listOf(Color(0xFF8ECAE6), Color(0xFF023047), Color(0xFFF77F00)),
                ratings = 4.4f
            ),
            image = R.drawable.ic_shoes_2
        ),
        Shoe(
            id = 15,
            name = "Future Rider",
            brand = brandList[2],
            color = Color(0xFFE63946),
            details = ShoesDetails(
                size = 10.5,
                price = 80.0,
                description = "The Puma Future Rider offers a vibrant color palette and a comfortable fit for everyday wear.",
                availableColors = listOf(Color(0xFFE63946), Color(0xFF457B9D), Color(0xFFA8DADC)),
                ratings = 4.3f
            ),
            image = R.drawable.ic_shoes_1
        ),
        Shoe(
            id = 16,
            name = "Club C 85",
            brand = brandList[3],
            color = Color(0xFF457B9D),
            details = ShoesDetails(
                size = 9.0,
                price = 75.0,
                description = "The Reebok Club C 85 is a classic tennis shoe that has stood the test of time.",
                availableColors = listOf(Color(0xFF457B9D), Color(0xFFE63946), Color(0xFFA8DADC)),
                ratings = 4.4f
            ),
            image = R.drawable.ic_shoes_4
        ),
        Shoe(
            id = 17,
            name = "HOVR Sonic 4",
            brand = brandList[4],
            color = Color(0xFF2A9D8F),
            details = ShoesDetails(
                size = 10.0,
                price = 110.0,
                description = "The Under Armour HOVR Sonic 4 offers a balanced ride with a responsive and cushioned feel.",
                availableColors = listOf(Color(0xFF2A9D8F), Color(0xFFE76F51), Color(0xFF264653)),
                ratings = 4.5f
            ),
            image = R.drawable.ic_shoes_3
        ),
        Shoe(
            id = 18,
            name = "GEL-Cumulus 22",
            brand = brandList[5],
            color = Color(0xFF2B2D42),
            details = ShoesDetails(
                size = 11.0,
                price = 130.0,
                description = "The Asics GEL-Cumulus 22 is known for its durability and comfort, making it a favorite among runners.",
                availableColors = listOf(Color(0xFF2B2D42), Color(0xFF8D99AE), Color(0xFFEDF2F4)),
                ratings = 4.5f
            ),
            image = R.drawable.ic_shoes_4
        ),
        Shoe(
            id = 19,
            name = "Epic React Flyknit 2",
            brand = brandList[0],
            color = Color(0xFFE76F51),
            details = ShoesDetails(
                size = 10.0,
                price = 150.0,
                description = "The Nike Epic React Flyknit 2 offers a smooth and responsive ride, perfect for long runs.",
                availableColors = listOf(Color(0xFFE76F51), Color(0xFF457B9D), Color(0xFFA8DADC)),
                ratings = 4.7f
            ),
            image = R.drawable.ic_shoes_1
        ),
        Shoe(
            id = 20,
            name = "Nite Jogger",
            brand = brandList[1],
            color = Color(0xFF9B2226),
            details = ShoesDetails(
                size = 10.0,
                price = 130.0,
                description = "The Adidas Nite Jogger is designed for comfort and visibility during nighttime running.",
                availableColors = listOf(Color(0xFF9B2226), Color(0xFF005F73), Color(0xFF001219)),
                ratings = 4.6f
            ),
            image = R.drawable.ic_shoes_2
        )
    )
}