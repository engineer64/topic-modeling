# Topic Modeling Pipeline for Multi-Corpus Trend Analysis

This repository contains the full source code, data, and scripts for the paper titled:

**“A Semi-Automatic Pipeline for Topic Modeling and Trend Analysis Across Technical Corpora”**

## Overview

The goal of this project is to provide a reproducible, modular pipeline for topic discovery and temporal trend tracking across large text corpora. While this implementation targets software architecture sources, the pipeline can be easily adapted to other domains.

The process includes:

- **Scraping:** Automated data collection from practitioner and academic sources.
- **Preprocessing:** Java-based text normalization and cleaning routines.
- **Modeling:** LDA topic modeling with automated topic count selection using perplexity.
- **Trend Analysis:** Time-based visualization of topic evolution across datasets.

## Data Sources

This implementation uses four corpora:
- StackOverflow (15,081 posts)
- DZone (12,923 articles)
- InfoQ (7,575 articles)
- IEEE Xplore (12,757 abstracts)

All text data is preprocessed and anonymized.

## How to Run

The notebooks in the `modeling/` directory can be executed independently for each corpus. Preprocessing utilities are found under `preprocessing/`, and scraping scripts are located in `scraping/`. Each component is modular and can be run in isolation or as part of a pipeline.

## Reproducibility

All configuration parameters (e.g., number of topics, random seeds, evaluation metrics) are logged. Topic modeling results are deterministic when using the provided random seeds.

## Citation

Citation pending.
