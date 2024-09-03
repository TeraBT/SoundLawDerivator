package list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CorrespondencePatternDetectionAlgorithm {

    /**
     * Based on pseudocode from Johann-Mattis List.
     */
    public List<AlignmentSite> getCorrespondencePatternList(List<AlignmentSite> alignmentSiteList) {
        List<AlignmentSite> siteList = alignmentSiteList.stream().sorted(Comparator.comparing(AlignmentSite::getMissingCount)).collect(Collectors.toList());
        List<AlignmentSite> patternList = new ArrayList<>();
        while (!siteList.isEmpty()) {
           List<AlignmentSite> compatibleList = new ArrayList<>();
           compatibleList.add(siteList.removeFirst());
           List<AlignmentSite> rest = List.copyOf(siteList);
           siteList.clear();
           for (AlignmentSite site : rest) {
               if (isCompatible(site, compatibleList)) {
                   compatibleList.add(site);
               } else {
                   siteList.add(site);
               }
           }
           patternList.addAll(compatibleList);
        }
        return patternList;
    }

    public boolean isCompatible(AlignmentSite site, List<AlignmentSite> compatibleList) {
        return true; // TODO: Implement method
    }
}
