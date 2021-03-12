#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/soundcard.h>

void main()
{
	int mixer;
	char *device = "/dev/mixer";
	
	if ( (mixer = open(device, O_RDWR)) < 0 ) printf("1.Could not open device\n");

	int devmask = 0;	
	if ( -1 == ioctl(mixer, SOUND_MIXER_READ_DEVMASK, &devmask) ) printf("2.Query failed\n");

	if ( (1 << SOUND_MIXER_MIC) & devmask ) printf("3.SOUND_MIXER_MIC is available.\n");
	else ("3.SOUND_MIXER_MIC is not available.\n");

	int recmask = 0;
	if ( -1 == ioctl(mixer, SOUND_MIXER_READ_RECMASK, &recmask) ) printf("4.Query failed\n");

	if ( (1 << SOUND_MIXER_MIC) & recmask ) printf("5.SOUND_MIXER_MIC is available.\n");
	else printf("5.SOUND_MIXER_MIC is not available.\n");

	int srcmask = 0;
	int src = SOUND_MIXER_MIC;
	srcmask = srcmask | (1 << src);
	if ( -1 == ioctl(mixer, SOUND_MIXER_WRITE_RECSRC, &srcmask) )
	{
  		printf("6.Set failed\n");
	}

	int index;
	char *sorc = "mic";
	char *names[SOUND_MIXER_NRDEVICES] = SOUND_DEVICE_LABELS;
	for (index = 0; index < SOUND_MIXER_NRDEVICES; index++)
	{
  		if ( 0 == strcmp(names[index], sorc) )
    		srcmask = srcmask | (1 << index);
	}
	
	index = 0;
	int recsrc = 0;
	if ( -1 == ioctl(mixer, SOUND_MIXER_READ_RECSRC, &recsrc) )
	{
  		printf("7.Query failed\n");
	}
	
	for (index = 0; index < SOUND_MIXER_NRDEVICES; index++)
	{
  		if ( (1 << index) & recsrc )
    		printf("8.Current recording source\n");
	}

	int stereomask = 0;
	int volume = 70;
	if ( -1 == ioctl(mixer, SOUND_MIXER_READ_STEREODEVS, &stereomask) )
	{
  		printf("9.Query failed\n");
	}

	if ( (1 << SOUND_MIXER_VOLUME) & stereomask )
  		volume = volume | (volume << 8);

	if ( -1 == ioctl(mixer, MIXER_WRITE(SOUND_MIXER_VOLUME), &volume) )
	{
  		printf("10.Set failed\n");
	}

	if ( -1 == ioctl(mixer, MIXER_READ(SOUND_MIXER_VOLUME), &volume) )
	{
		  printf("11.Query failed\n");
	}
	if ( (1 << SOUND_MIXER_VOLUME) & stereomask )
	{
  		printf("12.Current left master volume level is %d\n", (0xFF & volume) );
 		printf("12.Current right master volume level is %d\n", (0x00FF & volume) );
	}
	else
	{
  		printf("13.Current master volume level is %d\n", (0xFF & volume) );
	}
	printf("14.complete\n");
	close(mixer);
	printf("15.end\n");

}
