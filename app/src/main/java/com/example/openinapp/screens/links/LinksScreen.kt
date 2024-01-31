package com.example.openinapp.screens.links

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.openinapp.R
import com.example.openinapp.model.RecentLinksItem
import com.example.openinapp.model.TopLinksItem
import com.example.openinapp.ui.theme.BlueTopBar
import com.example.openinapp.ui.theme.ContentWhite
import com.example.openinapp.ui.theme.GreyNav
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun LinksScreen(navController: NavController,
                viewModel: LinksViewModel= hiltViewModel()
){
    Surface(){
        Column(modifier= Modifier
            .background(BlueTopBar)
            .fillMaxSize()){
                Titlebar()
            Column(modifier = Modifier
                .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
                .background(color = ContentWhite)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {

                    Analytics(viewModel)
                    Links(viewModel)
                    contactAndFaq()
            }
            }

    }
}

@Composable
fun contactAndFaq() {
    Row(
        Modifier
            .padding(start = 16.dp, top = 40.dp, end = 16.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFE8F1FF), shape = RoundedCornerShape(size = 8.dp)
            )
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = Color(0x1F4AD15F),
                shape = RoundedCornerShape(size = 8.dp)
            )
    )
    {
        Row(modifier = Modifier.padding(
            start = 12.dp,
            top = 20.dp,
            bottom = 20.dp
        )) {
            Image(
                painter = painterResource(id = R.drawable.whatsapp),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .padding(12.dp)
            )
            Text(
                text = "Talk with us",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                ),
            )
        }
    }
    Row(
        Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 150.dp)
            .border(
                width = 1.dp,
                color = Color(0x520E6FFF), shape = RoundedCornerShape(size = 8.dp)
            )
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = Color(0xFFE8F1FF),
                shape = RoundedCornerShape(size = 8.dp)
            )
    )
    {
        Row(
            modifier = Modifier.padding(
                start = 12.dp,
                top = 20.dp,
                bottom = 20.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.help),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .padding(12.dp)
            )
            Text(
                text = "Frequently Asked Questions",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                ),
            )
        }
    }
}


@Composable
fun LinksColumn(viewModel: LinksViewModel= hiltViewModel(),
                topLinks: Int) {
    if (!viewModel.isloadingRecentLink || !viewModel.isloadingTopLinks) {
        viewModel.topLinks()
        var listTop = viewModel.listTopLinks
        viewModel.recentLinks()
        var listRecent = viewModel.listRecentLinks
        Column(
            Modifier
                .padding(top = 28.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            for (i in 0..3) {
                if (topLinks == 1) {
                    LinksCardTop(listTop[i], viewModel)
                } else {
                    LinksCardRecent(listRecent[i], viewModel)
                }
            }
            Row(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFD8D8D8),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                Image(
                    painterResource(id = R.drawable.link), contentDescription = null,
                    modifier = Modifier.padding(start = 88.dp, top = 8.dp, bottom = 8.dp),
                )
                Text(
                    text = "View All Links",
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
    }
}



@Composable
fun LinksCardTop(topLinksItem: TopLinksItem, viewModel: LinksViewModel) {
    Column(
        Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(116.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))

    ){val TopLinks=topLinksItem
        Row(Modifier.padding(top=12.dp,start=12.dp,end=12.dp)){
            Image(painter= rememberImagePainter(data=TopLinks.originalImage), contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .width(48.dp)
                    .height(48.dp))
            Column(Modifier.padding(start=12.dp, top=2.dp, bottom=2.dp)){
                Text(
                    text = TopLinks.app,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Black,
                        ),
                    modifier= Modifier
                )
                Text(
                    text = formatDate( TopLinks.createdAt),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF999CA0),
                        ),
                    modifier= Modifier.padding(top=2.dp)
                )


            }
            Column(Modifier.padding(start= 92.dp,top=2.dp, bottom=2.dp)){
                Text(
                    text = (TopLinks.totalClicks.toString()),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Right,
                    )
                )
                Text(
                    text = "Clicks",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF999CA0),
                        ),
                    modifier=Modifier.padding(2.dp)
                )
            }
        }
        Row(
            Modifier
                .padding(top = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFA6C7FF),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color(0xFFE8F1FF),)

        ){
            Row(
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = TopLinks.webLink,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF0E6FFF),
                    ),
                    modifier = Modifier.padding(top = 3.5.dp)
                )
                Image(painter = painterResource(id = R.drawable.files),
                    contentDescription =null,
                    modifier= Modifier
                        .padding(start = 80.dp, 0.75.dp)
                        .width(24.dp)
                        .height(24.dp),
                )

            }
        }
    }
}

@Composable
fun LinksCardRecent(recentLinksItem: RecentLinksItem, viewModel: LinksViewModel) {
    val RecentLinks=recentLinksItem
    Column(
        Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .height(116.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))

    ){
        Row(Modifier.padding(top=12.dp,start=12.dp,end=12.dp)){
            Image(painter= rememberImagePainter(data=RecentLinks.originalImage), contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .width(48.dp)
                    .height(48.dp))
            Column(Modifier.padding(start=12.dp, top=2.dp, bottom=2.dp)){
                Text(
                    text = RecentLinks.app,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Black,
                    ),
                    modifier= Modifier
                )
                Text(
                    text = formatDate( RecentLinks.createdAt),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF999CA0),
                    ),
                    modifier= Modifier.padding(top=2.dp)
                )


            }
            Column(Modifier.padding(start= 92.dp,top=2.dp, bottom=2.dp)){
                Text(
                    text = RecentLinks.totalClicks.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Right,
                    )
                )
                Text(
                    text = "Clicks",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF999CA0),
                    ),
                    modifier=Modifier.padding(2.dp)
                )
            }
        }
        Row(
            Modifier
                .padding(top = 16.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFA6C7FF),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color(0xFFE8F1FF),)

        ){
            Row(
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = RecentLinks.webLink,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF0E6FFF),
                    ),
                    modifier = Modifier.padding(top = 3.5.dp)
                )
                Image(painter = painterResource(id = R.drawable.files),
                    contentDescription =null,
                    modifier= Modifier
                        .padding(start = 80.dp, 0.75.dp)
                        .width(24.dp)
                        .height(24.dp),
                )

            }
        }
    }
}



@Composable
fun Links(viewModel: LinksViewModel= hiltViewModel()) {
    var topLinks by rememberSaveable { mutableStateOf(1) }
    Row(modifier= Modifier.padding(top=40.dp,start=16.dp,end=16.dp)) {
        Row(modifier=Modifier.width(320.dp)) {
            if (topLinks == 1) {
                Button(
                    onClick = {
                        topLinks = 1
                    }, colors = ButtonDefaults.buttonColors(containerColor = BlueTopBar),
                    enabled = true,
                    modifier = Modifier
                        .height(36.dp)
                        .padding(end = 24.dp)
                ) {
                    Text(
                        "Top Links",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
                ClickableText(
                    text = AnnotatedString("Recent Links"),
                    onClick = {
                        topLinks = 0
                    },
                    modifier= Modifier.padding(top=6.dp,bottom=6.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = GreyNav,
                    )
                )
            } else {
                ClickableText(
                    text = AnnotatedString("Top Links"),
                    onClick = {
                        topLinks = 1
                    },
                    modifier= Modifier.padding(top=6.dp,bottom=6.dp,start=16.dp,end=32.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = GreyNav,
                    )
                )
                Button(
                    onClick = {
                        topLinks = 0
                    }, colors = ButtonDefaults.buttonColors(BlueTopBar),
                    enabled=true,
                    modifier = Modifier
                        .height(36.dp)
                ) {
                    Text(
                        "Recent Links",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }

        }
        Box(Modifier
            .border(width = 1.dp, color = Color(0xFFEBEBEB), shape = RoundedCornerShape(size = 8.dp))
            .width(36.dp)
            .height(36.dp)
            .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(size = 8.dp))
            .padding(start = 6.dp, top = 6.dp, end = 6.dp, bottom = 6.dp)){

            Image(painterResource(id = R.drawable.search),contentDescription = null,
                Modifier
                    .padding(1.dp)
                    .width(24.dp)
                    .height(24.dp)
            )

        }
    }
    LinksColumn(viewModel ,topLinks)
}



@Composable
fun Titlebar() {
    Column(modifier= Modifier
        .height(134.dp)
        .fillMaxWidth()
        .background(BlueTopBar),
        ) {
        Row(modifier = Modifier
            .padding(top =74.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            HeadingText()

            Image(painter = painterResource(id = R.drawable.button),
                contentDescription ="Settings",

                modifier = Modifier
                    .padding(start = 200.dp)
                    .size(40.dp)
            )


        }
    }
}


@Composable
fun HeadingText(){
    Text(
        text = "Dashboard",
        style = TextStyle(
            fontSize = 24.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFFFFFFFF),
        )
    )
}


@Composable
fun Analytics(viewModel: LinksViewModel= hiltViewModel()) {
    Column(Modifier.padding(top=32.dp, start=16.dp,end=16.dp)) {
        Greeting()
        Image(painterResource(id = R.drawable.graph),
            contentDescription = "graph",
            modifier = Modifier
                .padding(top = 24.dp)
                .height(200.dp)
                .fillMaxWidth())

        viewModel.dataResponseFun()
        var list= listOf<Card>(Card.Clicks, Card.Location, Card.Sources)
        if(viewModel.isloadingDataResponse){}
        else {
            list[0].Title = viewModel.dataResponseVal.todayClicks.toString()
            list[1].Title = viewModel.dataResponseVal.topLocation
            list[2].Title = viewModel.dataResponseVal.topSource
        }
        LazyRow(
            Modifier
                .padding(top = 20.dp)
                .height(120.dp)
                ){
            items(list){item->
                InsightCards(item.Icon!!,item.Heading!!,item.Title!!)
            }
        }
        Row(modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(48.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD8D8D8),
                shape = RoundedCornerShape(size = 8.dp)
            )
        ){
            Image(
                painterResource(id =R.drawable.arrows ), contentDescription = null,
                modifier= Modifier.padding(start=88.dp, top=8.dp,bottom=8.dp),)
            Text(
                text = "View Analytics",
                modifier= Modifier.padding(start=12.dp,top=12.dp,end=12.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    )
            )
        }
    }
}

@Preview
@Composable
fun InsightCards(Icon: Int=R.drawable.instagram, Heading:String="Instagram",Title: String="Instagram") {
    Card(modifier = Modifier
        .padding(end = 16.dp)
        .height(120.dp)
        .width(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
        ){
        Column ()
        {
            Image(painterResource(id =Icon),
                contentDescription = null,
                modifier=Modifier.padding(top=12.dp,start=12.dp))
            Text(text=Heading, style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                ), modifier = Modifier.padding(top=16.dp,start=12.dp)
            )
            Text(text = Title,style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999CA0),
                ), modifier = Modifier.padding(top=4.dp,start=12.dp)
            )
        }
    }
}

@Composable
fun Greeting() {
    Column(Modifier) {
        Text(
            "Good Morning", modifier = Modifier.padding(),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF999CA0),
            )
        )
        Row {
            Text(
                "Ajay Manwa", modifier = Modifier.padding(end=8.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
            Image(
                painter = painterResource(id = R.drawable.frame_7644),
                contentDescription = "Emoji",
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateSt: String): String {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val formattedDate = LocalDateTime.parse(dateSt, dateFormatter)

    return DateTimeFormatter.ofPattern("dd MMMM, yyyy | hh:mma").format(formattedDate)
}