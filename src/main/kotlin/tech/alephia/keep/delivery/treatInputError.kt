package tech.alephia.keep.delivery

//TODO: allow customizing this
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

fun<T> treatInputError2(
    io: InOut,
    fail: ReadResult<T>,
    noInputMessage: String,
    notAnIndex: String,
    notInBounds: String
) {
    io.frame {
        finish()

        when(fail) {
            is NoInput -> paragraph(noInputMessage)
            is NotAnIndex -> paragraph(notAnIndex)
            is NotInBounds -> paragraph(notInBounds)
        }

        promptContinue()
    }
}

fun choosePurpose(purpose: String) =
    if (purpose == "") "" else "$purpose "
