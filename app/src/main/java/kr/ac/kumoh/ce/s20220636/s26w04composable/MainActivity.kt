package kr.ac.kumoh.ce.s20220636.s26w04composable

import android.R.attr.name
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20220636.s26w04composable.ui.theme.S26W04ComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W04ComposableTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Counter()
        }
    }
}
@Composable
fun ColumnScope.Counter() {
    var count by remember { mutableIntStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .weight(1F)
            .padding(8.dp)
            .background(Color(0XFFE9F680)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0xFFFE7A36)),
            color = Color.White,
            fontSize = 100.sp,
            textAlign = TextAlign.Center,
        )

        Row {
            Button(
                modifier = Modifier
                    .weight(1f) // weight 준 상태
                    .padding(8.dp),
                onClick = {
                    count++
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_add), //android. 한거라 시스템 내에서 사용한거임 만약 따로 필요하면 리소스를 res - drawable같은 폴더에 넣고 사용
                    contentDescription = "증가 버튼"
                )
            }
            Button(
                modifier = Modifier
                    .padding(8.dp), // 감소버튼인데, 얘는 weight를 주지 않았기 때문에 저거대로 나옴
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_more),
                    contentDescription = "다른 버튼들"
                )
            }
        }

        AnimatedVisibility(expanded) {
            Row {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        count--
                        expanded = false
                    }
                ) {
                    Text("감소")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        count = 0
                        expanded = false
                    }
                ) {
                    Text("초기화")
                }
            }
        }
    }
}
