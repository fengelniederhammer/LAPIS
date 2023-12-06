package org.genspectrum.lapis.config

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val REFERENCE_GENOME_DEFAULT_FILENAME = "src/test/resources/config/reference-genomes.json"

class ReferenceGenomeTest {
    @Test
    fun `should read from file`() {
        val referenceGenome = ReferenceGenome.readFromFile(REFERENCE_GENOME_DEFAULT_FILENAME)
        assertThat(referenceGenome.nucleotideSequences.size, equalTo(1))
        assertThat(referenceGenome.nucleotideSequences[0].name, equalTo("main"))
    }

    @Test
    fun `should read from file through program args`() {
        val args = arrayOf("--referenceGenomeFilename=$REFERENCE_GENOME_DEFAULT_FILENAME")
        val referenceGenome = ReferenceGenome.readFromFileFromProgramArgsOrEnv(args)
        assertThat(referenceGenome.nucleotideSequences.size, equalTo(1))
        assertThat(referenceGenome.nucleotideSequences[0].name, equalTo("main"))
    }

    @Test
    fun `should throw if no reference genome filename is given in the args`() {
        val args = emptyArray<String>()
        assertThrows<IllegalArgumentException> { ReferenceGenome.readFromFileFromProgramArgsOrEnv(args) }
    }

    @Test
    fun `should generate spring application arguments`() {
        val referenceGenome = ReferenceGenome(
            listOf(ReferenceSequence("main"), ReferenceSequence("other_segment")),
            listOf(ReferenceSequence("gene1"), ReferenceSequence("gene2")),
        )
        val springArgs = referenceGenome.toSpringApplicationArgs()
        assertThat(springArgs[0], equalTo("--$REFERENCE_GENOME_SEGMENTS_APPLICATION_ARG_PREFIX=main,other_segment"))
        assertThat(springArgs[1], equalTo("--$REFERENCE_GENOME_GENES_APPLICATION_ARG_PREFIX=gene1,gene2"))
    }

    @Test
    fun `should detect single segmented sequence`() {
        val singleSegmented = ReferenceGenome(
            listOf(ReferenceSequence("main")),
            listOf(ReferenceSequence("gene1"), ReferenceSequence("gene2")),
        )
        assertThat(singleSegmented.isSingleSegmented(), equalTo(true))

        val multiSegmented = ReferenceGenome(
            listOf(ReferenceSequence("main"), ReferenceSequence("other_segment")),
            listOf(ReferenceSequence("gene1"), ReferenceSequence("gene2")),
        )
        assertThat(multiSegmented.isSingleSegmented(), equalTo(false))
    }
}
