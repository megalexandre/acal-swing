package br.org.acal.domain.usecase

fun interface Usecase<in input, out output> {

    fun execute(input: input): output
}
