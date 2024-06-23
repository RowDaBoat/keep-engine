package tech.alephia.keep.delivery

fun<T> treatInputError(
    io: InOut,
    fail: ReadResult<T>,
    article: String,
    subject: String,
    purpose: String = ""
) {
    io.frame {
        finish()

        when(fail) {
            is NoInput -> paragraph("Choose $article $subject ${choosePurpose(purpose)}using its [number].")
            is NotAnIndex -> paragraph("\"${fail.input}\" is not $article $subject [number].")
            is NotInBounds -> paragraph("There is no $subject at [${fail.index}]")
        }

        promptContinue()
    }
}

fun choosePurpose(purpose: String) =
    if (purpose == "") "" else "$purpose "
