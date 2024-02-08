package com.idnp.skinguardianapp.domain.model

import com.idnp.skinguardianapp.R

class ProductsProvider() {

    companion object {
        val productList = listOf<Product>(
            Product(
                "Tónico Glow Facial",
                "Revitaliza la piel para reducir el aspecto de los poros y conferirle al cutis un efecto suavizante, rellenador e iluminador",
                "https://uperfect.pe/cdn/shop/files/imagen_2023-05-08_122054925.png?v=1683566596"
            ),
            Product(
                "Gel limpiador espumoso",
                "Un limpiador facial espumoso es ideal para eliminar el exceso de grasa",
                "https://www.cerave.pe/-/media/project/loreal/brand-sites/cerave/americas/pe/new_product/new_product_700x875/limpieza/gel-limpiador-espumoso-473ml/3337875597357.jpg?rev=-1"
            ),
            Product(
                "Bepanthen",
                "Acelera el proceso de regeneración de la piel",
                "https://www.bepanthen.com.pe/sites/g/files/vrxlpx49126/files/2023-12/crema.png?imwidth=5000"
            ),
            Product(
                R.string.titulo4.toString(),
                R.string.texto4.toString(),
                "https://i.ebayimg.com/images/g/O6gAAOSwKhJgeYXW/s-l1600.png"
            ),
            Product(
                R.string.titulo5.toString(),
                R.string.texto5.toString(),
                "https://dumashe.com/wp-content/uploads/2021/10/Crema-Reparadora-con-extractos-ALOE-VERA-by-Bioaqua-foto-2.jpg"
            ),
            Product(
                R.string.titulo6.toString(),
                R.string.texto6.toString(),
                "https://m.media-amazon.com/images/I/71Y4XA8PHOL.jpg"
            ),
            Product(
                R.string.titulo7.toString(),
                R.string.texto7.toString(),
                "https://static.beautytocare.com/media/catalog/product/n/e/neutrogena-hydro-boost-cleanser-water-gel-200ml_2.jpg"
            ),
            Product(
                R.string.titulo8.toString(),
                R.string.texto8.toString(),
                "https://es.aveeno.com/sites/aveeno_us_2/files/product-images/ave_00381371187805_oatmask_pumpkin_1.7oz_obwb.png"
            ),
            Product(
                R.string.titulo9.toString(),
                R.string.texto9.toString(),
                "https://oechsle.vteximg.com.br/arquivos/ids/7174435-1500-1500/image-235e2668735d405f80a8790de35ccc3d.jpg?v=637801530417570000"
            ),
            Product(
                R.string.titulo10.toString(),
                R.string.texto10.toString(),
                "https://images-cdn.ubuy.co.in/658c2600eaeb161a114e6a14-dove-sensitive-skin-long-lasting-gentle.jpg"
            ),

        )
    }
}