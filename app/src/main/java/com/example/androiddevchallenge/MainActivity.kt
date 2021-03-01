/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                navigator()
            }
        }
    }
}

// Start building your app here!

val pets = arrayListOf<petdesc>(
    petdesc(
        name = "Rockie",
        breed = "Rottweiler",
        Source = "INDEPENDENT",
        status = "Available",
        Temperament = "Playful, Friendly",
        Condition = "Healthy",
        gender = "Male",
        location = "Delhi",
        age = "5 years, 7 months and 2 days old",
        imageid = R.drawable.b,
        Desc = "Rockie, this very sweet-looking Rottweiler at the Sanjay Gandhi Animal Care Centre, Raja Garden, New Delhi, needs a loving, Rottweiler-experienced family to give him a forever home. According to the shelter staff, Rockie is both people and dog friendly. He is also playful and active, refusing to sit still for photographs! When Rockie was first dropped off at the shelter, he had a huge maggot wound under his tail, but it has healed completely and our boy is fit  and ready to go! He is healthy and neutered."

    ),
    petdesc(
        name = "Billo",
        breed = "Spitz",
        status = "Available",
        Source = "INDEPENDENT",
        Temperament = "Friendly",
        Condition = "Healthy",
        gender = "Male",
        location = "Delhi",
        age = "5 years, 7 months and 2 days old",
        imageid = R.drawable.a,
        Desc = "Billo was found in Paschim Puri, west Delhi,  being terrorized by the local dogs. He is now at the Sanjay Gandhi Animal Care Centre, Raja Garden, Delhi, waiting, hoping for a kind family to give him a forever home. Billo's hind legs are weak, they don't support his weight so he has trouble walking. It's possible he was raised on smooth, slippery floors, on which it's difficult for dogs' paws to get a grip. One doesn't really know why the legs are weak. However, he is perfectly healthy otherwise and will do well in a home without stairs, and carpeted floors. Billo is a friendly boy and will fit in nicely with a family looking to adopt a small breed dog."

    ),
    petdesc(
        name = "Bread & Butter (tentative)",
        breed = "Indian Pariah Dog",
        status = "Available",
        Source = "INDEPENDENT",
        Temperament = "Playful, Friendly",
        Condition = "Healthy",
        gender = "Male",
        location = "Hyderabad",
        age = "7 months and 6 days old",
        imageid = R.drawable.breadbutter,
        Desc = "These bundles of joy are Bread (male) & Butter (female). They are currently looking for a forever home. They'll fill the house with the cuteness and playfulness. If you feel you are the one please come forward. Share and spread the word. Pre and post-adoption house checks and other adoption formalities are mandatory. Available for adoption in Hyderabad."
    ),
    petdesc(
        name = "Mili",
        breed = "Indian Pariah Dog",
        status = "Not available",
        Source = "INDEPENDENT",
        Temperament = "Playful, Friendly",
        Condition = "Healthy",
        gender = "Female",
        location = "Gurgaon",
        age = "5 months and 15 days old",
        imageid = R.drawable.mili,
        Desc = "Mili, the cute eyed pupper will fill your home with her sweetness. She is supaw friendly, and her bonus point is that Mili is so cute. But, her foster parents are no longer able to take care of her. So, she is now looking for a forever home where she can play around and be cute. If you feel you are the one please come forward. Share and spread the word. Pre and post-adoption house checks and other adoption formalities are mandatory. Available for adoption in Gurgaon."
    ),
    petdesc(
        name = "Nugget",
        breed = "Indian Pariah Dog",
        status = "Available",
        Source = "INDEPENDENT",
        Temperament = "Playful, Friendly",
        Condition = "Healthy, Vaccinated",
        gender = "Female",
        location = "New Delhi",
        age = "2 months and 17 days old",
        imageid = R.drawable.nugget,
        Desc = "This is Nugget! she's a 2 months old Indie girl. Nugget was hit by a speeding vehicle and is currently recovering from a leg injury in foster care. She has been dewormed, protected against parvo, distemper, and rabies. Friendly and a playful kid, Nugget loves to quietly enjoy the company of humans. Any family will be lucky to have her. If you feel you are the one please come forward. Share and spread the word. Pre and post-adoption house checks and other adoption formalities are mandatory. Available for adoption in Delhi/NCR."
    )

)

// ------navigator-----------------------------------
@Composable
fun navigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "petlist",
        builder = {
            composable("petlist") { petlist(navController) }
            composable(
                "petDetails/{petid}",
                arguments = listOf(navArgument("petid") { type = NavType.IntType })
            ) { backStack ->
                val petid = backStack.arguments!!.getInt("petid")
                petDetails(petid = petid, navController = navController)
            }
        }
    )
}

@Composable
fun petcard(
    petname: String,
    status: String,
    imageid: Int,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(5))
            .clickable(onClick = onClick)
    ) {
        Surface(
            modifier = Modifier
                .height(255.dp)
                .fillMaxWidth(),

        ) {

            Image(
                painter = painterResource(id = imageid), contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(225.dp)
                    .alpha(0.8f),
                contentScale = ContentScale.Crop

            )

            Text(
                text = petname,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                style = MaterialTheme.typography.h3,

                modifier = Modifier
                    .padding(start = 7.dp, bottom = 7.dp)
                    .wrapContentHeight(Alignment.Bottom),
//                color = Color(3,3,3,60)

            )
            val col = if (status.toLowerCase().equals("available")) Color.Green else Color.Red
            Text(
                text = status,
                color = col,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
                    .padding(end = 10.dp)

            )
        }
    }
}

@Composable
fun petlist(navController: NavController) {
    LazyColumn {
        itemsIndexed(
            items = pets
        ) {
            index, pet ->
            petcard(
                petname = pet.name, imageid = pet.imageid, status = pet.status,
                onClick = { navController.navigate("petDetails/$index") }
            )
        }
    }
}

// ////////////////////////////////////////////////////////////

// Detail page view//
@Composable
fun petDetails(
    petid: Int,
    navController: NavController
) {

    MaterialTheme(colors = MaterialTheme.colors) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, top = 0.dp, bottom = 14.dp)

        ) {
            Surface(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Top)
            ) {

                Image(
                    painter = painterResource(id = pets[petid].imageid),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeightIn(225.dp),
                    contentScale = ContentScale.Crop,

                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(start = 3.dp, top = 10.dp)
                        .wrapContentHeight(Alignment.Top)
                        .wrapContentWidth(Alignment.Start)
                ) {
                    Icon(Icons.Filled.ArrowBack, "")
                }
            }
            Text(
                text = "Basic Information",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )

            Column(
                Modifier
                    .background(MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

            ) {

                val name = pets[petid].name
                val breed = pets[petid].breed
                val status = pets[petid].status
                val source = pets[petid].Source
                val temperament = pets[petid].Temperament
                val condition = pets[petid].Condition
                val gender = pets[petid].gender
                val location = pets[petid].location
                val age = pets[petid].age
                val desc = pets[petid].Desc

                Text(
                    text = "Dog Name:        $name\n" +
                        "Breed Name:     $breed\n" +
                        "Source:         $source\n" +
                        "Status:        $status\n" +
                        "Temperament:   $temperament\n" +
                        "Condition:      $condition\n" +
                        "Gender:         $gender\n" +
                        "Location:       $location\n" +
                        "Age:            $age",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    lineHeight = 30.sp,

                )
                Text(
                    text = "Aboout",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = desc,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Preview("Light Theme",)
@Composable
fun LightPreview() {
    MyTheme {
//        petlist()
//        petDetails(petid = 0)
    }
}

// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
// }
