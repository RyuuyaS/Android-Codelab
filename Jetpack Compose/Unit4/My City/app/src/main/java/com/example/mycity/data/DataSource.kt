package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Category
import com.example.mycity.model.Recommendation

object DataSource {
    val categoryList = listOf<Category>(
        Category(0, R.string.category_one, R.drawable.food),
        Category(1, R.string.category_two, R.drawable.drink),
        Category(2, R.string.category_three, R.drawable.view),
        Category(3, R.string.category_five, R.drawable.company)
    )

    private val recommendationData = listOf<List<Recommendation>>(
        //food
        listOf(
            Recommendation(
                0,
                R.string.category_one_recommendation_one,
                R.drawable.bun,
                R.string.description
            ),
            Recommendation(
                1,
                R.string.category_one_recommendation_two,
                R.drawable.pho,
                R.string.description
            ),
            Recommendation(
                2,
                R.string.category_one_recommendation_three,
                R.drawable.bun_rieu_cua,
                R.string.description
            ),
            Recommendation(
                3,
                R.string.category_one_recommendation_four,
                R.drawable.com,
                R.string.description
            ),
            Recommendation(
                4,
                R.string.category_one_recommendation_five,
                R.drawable.banh_canh,
                R.string.description
            ),
        ),
        //drink
        listOf(
            Recommendation(
                0,
                R.string.category_two_recommendation_one,
                R.drawable.cafe,
                R.string.description
            ),
            Recommendation(
                1,
                R.string.category_two_recommendation_two,
                R.drawable.matcha_latte_4598_i_1, R.string.description
            ),
            Recommendation(
                2,
                R.string.category_two_recommendation_three,
                R.drawable.bac_xiu_la_gi_nguon_goc_va_cach_lam_bac_xiu_thom_ngon_don_gian_tai_nha_5_800x529,
                R.string.description
            ),
            Recommendation(
                3,
                R.string.category_two_recommendation_four,
                R.drawable.thumb_1200x676_7,
                R.string.description
            ),
            Recommendation(
                4,
                R.string.category_two_recommendation_five,
                R.drawable.quat_xanh_lac_sua,
                R.string.description
            )
        ),
        //view
        listOf(
            Recommendation(
                0,
                R.string.category_three_recommendation_one,
                R.drawable.hoang_thanh_thang_long_834, R.string.description
            ),
            Recommendation(
                1,
                R.string.category_three_recommendation_two,
                R.drawable.chua_bai_dinh_ninh_binh_495029, R.string.description
            ),
            Recommendation(
                2,
                R.string.category_three_recommendation_three,
                R.drawable.bai_vieng_lang_bac, R.string.description
            ),
            Recommendation(
                3,
                R.string.category_three_recommendation_four,
                R.drawable.dinh_doc_lap_1,
                R.string.description
            ),
            Recommendation(
                4,
                R.string.category_three_recommendation_five,
                R.drawable.landmark81,
                R.string.description
            )
        ),
        //company
        listOf(
            Recommendation(
                0,
                R.string.category_four_recommendation_one,
                R.drawable.fpt,
                R.string.description
            ),
            Recommendation(
                1,
                R.string.category_four_recommendation_two,
                R.drawable.vnpt,
                R.string.description
            ),
            Recommendation(
                2,
                R.string.category_four_recommendation_three,
                R.drawable.viettle,
                R.string.description
            ),
            Recommendation(
                3,
                R.string.category_four_recommendation_four,
                R.drawable.mobifone,
                R.string.description
            ),
            Recommendation(
                4,
                R.string.category_four_recommendation_five,
                R.drawable.vingroup,
                R.string.description
            ),
        ),
    )
    val abc = HashMap<Int, List<Recommendation>>().apply {
        for (i in recommendationData.indices) {
            this[i] = recommendationData[i]
        }
    }
}