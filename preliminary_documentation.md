# Code Structure (Preliminary Documentation)

## Code Structure Overview

The project [sound_law_derivator](#) is organized into several packages and directories, each contributing to different aspects of the sound law derivation process. The main components of the project are:

- **/src/auxiliary:** This package contains classes responsible for handling various auxiliary tasks, such as sequence comparison, string preprocessing, and parallel processing. It includes algorithms like Levenshtein distance and Needleman-Wunsch, which are critical for sequence alignment.

- **/src/mapping:** This package manages the conversion and manipulation of linguistic symbols and orthographies. It includes classes for handling the International Phonetic Alphabet (IPA) and other phonetic and orthographic representations.

- **/src/soundsystem:** This package is dedicated to modeling phonetic features and sound changes. It includes classes representing individual phonetic elements, such as consonants and vowels, and utilities for manipulating these elements in the context of sound law analysis.

- **/src/naive:** This package contains the naive algorithm developed as part of this thesis.

- **/src/list:** This package contains a modified variant of List's algorithm \cite{list_2019}.

- **!!!TODO: /visualization:** This ...

In the following subsections, we provide detailed descriptions of the classes within the `soundsystem` package, which are the core of the model.

## Sound System

### FFE.java - FractionFieldElement

The `FFE` (FractionFieldElement) class is designed to handle arithmetic operations on fractional values, which constitute the phone vectors. It implements the `FieldElement<T>` interface from the Apache Commons Math library as `FieldElement<FFE>`

<!--
### VowelQualityVector.java

The `VowelQualityVector` class was originally created to encapsulate various properties of vowels into a vector format. Although this class is now deprecated, it represents key attributes of vowels, including:
- **Backness:** The horizontal position of the tongue during vowel articulation.
- **Openness:** The vertical position of the tongue, indicating how open or closed the mouth is.
- **Roundedness:** Whether the lips are rounded during the production of the vowel.
- **Length:** The duration of the vowel sound.
- **Nasalization:** The presence of nasal airflow during vowel production.

This vector-based representation facilitated the systematic comparison of vowels, aiding in phonetic analysis tasks within the system.
-->

### Vowel.java

The `Vowel` class is the current implementation for representing vowels within the sound system. It inherits from the `Phone` class.

### Consonant.java

The `Consonant` class represents consonants within the phonetic system. In analogy to the `Vowel` class, it represents the phonetic features of consonants, such as place and manner of articulation. It inherits from the `Phone` class.

### Phone.java

The `Phone` class represents generic phones within the sound system. Both `Vowel` and `Consonant` inherit from it.

### PlaceholderPhone.java

The `PlaceholderPhone` class is used to represent temporary or placeholder sounds. These are `EMPTY_PHONE`, `UNKNOWN_PHONE`, and `UNDEFINED_PLACEHOLDER`. This class is employed during the preprocessing stage of the algorithm, where certain phonetic elements may need to be temporarily substituted.

### SoundLawMatrix.java

The `SoundLawMatrix` class is responsible for managing the matrix representation of sound laws. It contains several utilities for matrix manipulation.

### FFEField.java

The `FFEField` is needed for technical reasons in the implementation of `FFE`.

## Mapping

The `mapping` package contains classes for translating between different linguistic representations, like the mapping between orthographic representations and their corresponding phonetic symbols. It defines necessary classes for linguistic abstractions.

### IPA.java

The `IPA` class implements the `PhoneticAlphabet` interface, specifically mapping between IPA symbols and their corresponding phonetic representations within the system. This class utilizes two primary mappings:

- **symbolToPhoneMap:** Maps each `IPASymbol` to a corresponding `Phone` object, allowing the system to translate IPA symbols into computational representations.
- **phoneToSymbolMap:** Provides the reverse mapping, converting `Phone` objects back into their IPA symbol equivalents.

### IPASymbol.java

The `IPASymbol` class defines enums of IPA symbols, which are used for the mappings in the `IPA` class.

### LatinOrthography.java

The `LatinOrthography` class implements the `Orthography` interface, handling the mapping between Latin orthographic representations (letters and letter combinations) and their corresponding `IPASymbol` lists. This class maintains two mappings:

- **representativeToSymbolMap:** Maps Latin orthographic sequences to lists of corresponding `IPASymbols`.
- **symbolToRepresentativeMap:** Provides the inverse mapping, allowing the conversion of lists of `IPASymbols` back into their Latin orthographic forms.

### Orthography.java

The `Orthography` interface defines the contract for any class that needs to handle the mapping between orthographic representations and phonetic symbols. Implementations of this interface, such as `LatinOrthography`, are required to provide methods for converting between strings (or lists of symbols) and their corresponding phonetic representations. This interface ensures consistency across different orthographic systems that might be implemented in the future.

### PhoneticAlphabet.java

The `PhoneticAlphabet` interface specifies the methods required for any phonetic alphabet implementation. It defines methods to:

- Map an `IPASymbol` to a `Phone`.
- Map a `Phone` back to an `IPASymbol`.
- Retrieve the set of all `IPASymbols` and `Phones` that the system recognizes.

The `IPA` class is an implementation of this interface.

### SigmaMapper.java

The `SigmaMapper` class serves as a bridge between orthographic sequences and their corresponding phonetic representations. This class provides methods to:

- Convert a string of orthographic symbols into a sequence of `IPASymbols`.
- Map this sequence to a list of `Phone` objects.

## Naive Algorithm - NaiveDerivationAlgorithm.java

The `NaiveDerivationAlgorithm` class, located in the `src/naive` package, is responsible for deriving potential sound law candidates by comparing aligned sequences of phonetic symbols. It processes both phonetic symbols (`IPASymbol`) and character-based sequences, identifying differences and generating candidate sound laws. The algorithm simplifies the derivation process by providing a baseline method for identifying changes between corresponding segments in the aligned sequences. Additionally, it aggregates these sound law candidates and ranks them based on their frequency of occurrence.

## List's Algorithm - CorrespondencePatternDetectionAlgorithm.java

The `CorrespondencePatternDetectionAlgorithm` class, located in the `src/list` package, implements a modified version of List’s algorithm \cite{list_2019} for detecting correspondence patterns in aligned linguistic sequences. This algorithm iterates through alignment sites, merges compatible patterns into larger groups, and identifies patterns of sound correspondences.

## Auxiliary Classes

The `auxiliary` package provides utilities and algorithms that support the core functionalities of the project. These classes include sequence comparison algorithms, string preprocessing utilities, and input/output operations.

### LevenshteinDistance.java

The `LevenshteinDistance` class computes the Levenshtein distance \cite{levenshtein_1965}, a method used for calculating the minimum number of single-character edits (insertions, deletions, or substitutions) required to transform one string into another.

### NeedlemanWunschAlgorithm.java

The `NeedlemanWunschAlgorithm` class provides an implementation of the Needleman-Wunsch algorithm, a dynamic programming algorithm used for global sequence alignment \cite{needleman_wunsch_1970}, which is commonly used in bioinformatics.

### SequenceComparator.java

The `SequenceComparator` class is a core utility for comparing sequences, utilizing both the `LevenshteinDistance` and `NeedlemanWunschAlgorithm` classes.

### StringPreprocessor.java

The `StringPreprocessor` class is responsible for preparing strings from input files by normalizing, tokenizing, and mapping them to `IPASymbol` sequences.

### TextReaderWriter.java

The `TextReaderWriter` class is used for reading files containing textual data.

### XMLParser.java

The `XMLParser` class is used for parsing XML files.

### Parallelization Subdirectory

The `parallelization` subdirectory within the `auxiliary` package contains classes designed to optimize the computational efficiency of the sound law derivator by distributing tasks across multiple threads or processes.

#### LevenshteinWorker.java

The `LevenshteinWorker` class is a specialized worker class designed to perform Levenshtein distance calculations in parallel.

#### NeedlemanWunschWorker.java

The `NeedlemanWunschWorker` class is analogous to the `LevenshteinWorker`, but instead, it executes the Needleman-Wunsch algorithm.

#### RepresentativeWorkerOrganizer.java

The `RepresentativeWorkerOrganizer` class is responsible for coordinating the activities of worker instances involved in sequence comparison tasks. This class ensures that the workload is evenly distributed among workers and that the results are aggregated.

#### SymbolWorkerOrganizer.java

The `SymbolWorkerOrganizer` class is analogous to the `RepresentativeWorkerOrganizer` class but processes `IPASymbol` lists instead of strings.

#### WorkerOrganizer.java

The `WorkerOrganizer` class provides methods for launching, monitoring, and coordinating worker threads. Both the `RepresentativeWorkerOrganizer` and `SymbolWorkerOrganizer` class inherit from this class.
