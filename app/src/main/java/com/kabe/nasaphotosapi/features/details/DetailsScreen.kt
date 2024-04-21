package com.kabe.nasaphotosapi.features.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kabe.nasaphotosapi.R
import com.kabe.nasaphotosapi.domain.NasaPhoto
import com.kabe.nasaphotosapi.ui.theme.Black
import com.kabe.nasaphotosapi.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    nasaPhoto: NasaPhoto,
) {
    DetailsScreenView(
        navigator = navigator,
        nasaPhoto = nasaPhoto
    )
}

@Composable
fun DetailsScreenView(
    navigator: DestinationsNavigator,
    nasaPhoto: NasaPhoto,
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (
            btnBack,
            imgRover,
            txtCameraName,
            txtRoverName,
            txtLandingDate,
            txtLaunchDate,
            txtStatus,
            txtTotalPhotos,
        ) = createRefs()

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(btnBack) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(
                    start = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                )
                .size(24.dp)
                .clickable { navigator.navigateUp()},
            tint = Black
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(nasaPhoto.imgUrl.replace("http://", "https://"))
                .crossfade(true)
                .build(),
            placeholder = null,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imgRover) {
                    centerHorizontallyTo(parent)
                    top.linkTo(btnBack.bottom)
                }
                .padding(top = MaterialTheme.spacing.large)
                .height(150.dp)
                .width(150.dp)
        )

        Text(
            modifier = Modifier
                .constrainAs(txtCameraName) {
                    start.linkTo(parent.start)
                    top.linkTo(imgRover.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_camera_name)} ${nasaPhoto.camera?.fullName}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtRoverName) {
                    start.linkTo(parent.start)
                    top.linkTo(txtCameraName.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_rover_name)} ${nasaPhoto.rover?.name}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtLandingDate) {
                    start.linkTo(parent.start)
                    top.linkTo(txtRoverName.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_landing_date)} ${nasaPhoto.rover?.landingDate}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtLaunchDate) {
                    start.linkTo(parent.start)
                    top.linkTo(txtLandingDate.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_launch_date)} ${nasaPhoto.rover?.launchDate}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtStatus) {
                    start.linkTo(parent.start)
                    top.linkTo(txtLaunchDate.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_status)} ${nasaPhoto.rover?.status}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )

        Text(
            modifier = Modifier
                .constrainAs(txtTotalPhotos) {
                    start.linkTo(parent.start)
                    top.linkTo(txtStatus.bottom)
                }
                .padding(
                    start = MaterialTheme.spacing.large + MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.medium
                ),
            text = "${stringResource(id = R.string.label_total_photos)} ${nasaPhoto.rover?.totalPhotos.toString()}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 14.sp,
                color = Black,
                fontWeight = FontWeight.W600
            )
        )
    }
}
