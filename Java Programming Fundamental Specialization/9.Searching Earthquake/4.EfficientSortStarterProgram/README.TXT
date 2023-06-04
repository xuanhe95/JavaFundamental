Assignment Week2
Earthquakes: Sorting Algorithms

类 QuakeEntry
方法 compareTo
通过返回正数/负数来决定对比的顺序
首先比较magnitude，然后比较depth

类 DifferentSorters
通过方法 Collections.sort
传入不同的Comparator

类 TitleLastAndMagnitudeComparator/TitleAndDepthComparator
Comparator根据不同的比较方法返回正数/负数