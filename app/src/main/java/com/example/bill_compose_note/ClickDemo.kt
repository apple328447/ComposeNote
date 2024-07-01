package com.example.bill_compose_note

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.outlinedCardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bill_compose_note.util.IntentAppUtil

@Composable
fun getContentStyle(): TextStyle {
    return TextStyle(
        color = colorResource(
            id = R.color.content_yellow
        ),
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}


/**
 * 「indication」「interactionSource」，都要設置，因為都需要給值，如果只設置一個會被判斷成另一個function
 *               .clickable(
 *                         onClick = {
 *
 *                         },
 *                         indication = null,
 *                         interactionSource = remember { MutableInteractionSource() }
 *                     )
 *
 *
 *
 * indication： 表示点击时的视觉反馈效果。它是一个 Indication 类型的参数，用于定义点击时显示的效果。通常情况下，可以设置为 indication = null 来禁用默认的点击反馈效果。默认情况下，Modifier.clickable 会显示一个具有涟漪效果的点击反馈。
 *
 * interactionSource： 表示点击事件的源头。默认情况下，Compose 会为每个可点击的组件创建一个新的 MutableInteractionSource。如果要共享相同的 interactionSource，可以在多个组件中传递相同的 interactionSource 实例。
 * */


/**
 * 新增跳轉(Intent)手機APP的方式
 * */
@Preview
@Composable
fun RemoveClickEffect() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(horizontal = 23.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFF141212)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
                .clickable(
                    onClick = {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clickable(
                        onClick = {
                            println("Click IG!")
                            val username = "your_instagram_username"  // 替换为你的 Instagram 用户名
                            val intent = Intent(Intent.ACTION_VIEW)
                            context.let {
                                if (IntentAppUtil.isInstagramInstalled(it)) {
                                    intent.data = Uri.parse("http://instagram.com/_u/$username")
                                    intent.setPackage("com.instagram.android")
                                    it.startActivity(intent)
                                } else {
                                    //打開Play商店的IG
//                                    val playStoreIntent = Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android")
//                                    )
//                                    it.startActivity(playStoreIntent)

                                    // 打开 Instagram 网站
                                    intent.data = Uri.parse("http://instagram.com/$username")
                                    it.startActivity(intent)
                                }
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center,
            ) {

                val bgColor = Color(0x4DDFE2DD)
                Card(
                    modifier = Modifier.size(43.dp),
                    shape = CircleShape,
                    colors = outlinedCardColors(
                        bgColor,
                        bgColor,
                        bgColor,
                        bgColor
                    ),//CardColors 因為internal所以無法使用
                ) {

                }
                Image(
                    painter = painterResource(id = R.drawable.ic_instgram),
                    contentDescription = "IG",
                    modifier = Modifier.size(36.dp, 36.dp)
                )
            }

            Text(
                modifier = Modifier
                    .height(20.dp)
                    .padding(top = 4.dp)
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                text = "IG",
                maxLines = 2,
                style = getContentStyle(),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable(
                    onClick = {
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clickable(
                        onClick = {
                            println("Click Twitter!")
                            context.let {
                                if (IntentAppUtil.isTwitterInstalled(it)) {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.data =
                                        Uri.parse("twitter://user?screen_name=your_twitter_handle")
                                    it.startActivity(intent)
                                } else {
                                    val playStoreIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android")
                                    )
                                    it.startActivity(playStoreIntent)

                                    //或是 打開 Twitter 網站
                                    //val intent = Intent(Intent.ACTION_VIEW)
                                    //intent.data = Uri.parse("https://twitter.com/your_twitter_handle")
                                }
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center,

                ) {

                val bgColor = Color(0x4DDFE2DD)
                Card(
                    modifier = Modifier.size(43.dp),
                    shape = CircleShape,
                    colors = outlinedCardColors(
                        bgColor,
                        bgColor,
                        bgColor,
                        bgColor
                    ),//CardColors 因為internal所以無法使用
                ) {}
                Image(
                    painter = painterResource(id = R.drawable.ic_twitter),
                    contentDescription = "transfer",
                    modifier = Modifier.size(36.dp, 36.dp)
                )
            }

            Text(
                modifier = Modifier
                    .height(20.dp)
                    .padding(top = 4.dp)
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                text = "Twitter",
                maxLines = 2,
                style = getContentStyle(),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable(
                    onClick = {
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clickable(
                        onClick = {
                            println("Click Google Play!")
                            context.let {
                                val playStoreIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps")
                                )
                                it.startActivity(playStoreIntent)
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center,

                ) {

                val bgColor = Color(0x4DDFE2DD)
                Card(
                    modifier = Modifier.size(43.dp),
                    shape = CircleShape,
                    colors = outlinedCardColors(
                        bgColor,
                        bgColor,
                        bgColor,
                        bgColor
                    ),//CardColors 因為internal所以無法使用
                ) {

                }
                Image(
                    painter = painterResource(id = R.drawable.ic_googlepaly),
                    contentDescription = "transfer",
                    modifier = Modifier.size(30.dp, 30.dp)
                )
            }

            Text(
                modifier = Modifier
                    .height(20.dp)
                    .padding(top = 4.dp)
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                text = "Google Play",
                maxLines = 2,
                style = getContentStyle(),
            )

        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable(
                    onClick = {
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clickable(
                        onClick = {
                            println("Click Telegram!")
                            context.let {
                                if (IntentAppUtil.isTelegramInstalled(it)) {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    //intent.data = Uri.parse("tg://msg?text=Hello%20World&to=username")//打開並且打好訊息
                                    intent.data = Uri.parse("tg://resolve")//只打開不做事
                                    it.startActivity(intent)
                                } else {
                                    val playStoreIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                                    )
                                    it.startActivity(playStoreIntent)
                                }
                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center,

                ) {

                val bgColor = Color(0x4DDFE2DD)
                Card(
                    modifier = Modifier.size(43.dp),
                    shape = CircleShape,
                    colors = outlinedCardColors(
                        bgColor,
                        bgColor,
                        bgColor,
                        bgColor
                    ),
                ) {

                }
                Image(
                    painter = painterResource(id = R.drawable.ic_telegram),
                    contentDescription = "Telegram",
                    modifier = Modifier.size(36.dp, 36.dp)
                )
            }

            Text(
                modifier = Modifier
                    .height(20.dp)
                    .padding(top = 4.dp)
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                text = "Telegram",
                maxLines = 2,
                style = getContentStyle(),
            )

        }

    }
}
